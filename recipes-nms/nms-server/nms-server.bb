DESCRIPTION = "NMS Server"

S = "${WORKDIR}/git"
B = "${WORKDIR}/build"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += " \
	git://cellgain.ddns.net:30000/cellgain-public/nms-server.git;protocol=http; \
	file://eth-conf.sh \
	file://start-ap.sh \
	file://stop-ap.sh \
	file://emmc-install.sh \
	file://fw_env.config \
	file://img-yocto.sdimg.tar.gz;unpack=0 \
	file://70-custom-name.rules \
	file://nms-server.service \
	file://nms-server-remote.service \
	"
SRCREV = "${AUTOREV}"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

DEPENDS += "	go-cross-${TARGET_ARCH} \	   
		libusb1 \
		pkgconfig \
	"
RDEPENDS_${PN} = "bash"

SRC_URI_append += "file://nms-server.service \
                 "
inherit systemd pkgconfig

SYSTEMD_SERVICE_${PN} = "nms-server.service \
			"

FILES_${PN} += "/data/nms-server/network/eth-conf.sh \
		/data/nms-server/network/start-ap.sh \
		${sysconfdir}/nms-server/stop-ap.sh \
		${sysconfdir}/emmc/emmc-install.sh \
		${sysconfdir}/emmc/fw_env.config \
		${sysconfdir}/emmc/img-yocto.sdimg.tar.gz \
		${sysconfdir}/udev/rules.d/70-custom-name.rules \
               "

# Go binaries produce unexpected effects that the Yocto QA mechanism doesn't
# like. We disable those checks here.
INSANE_SKIP_${PN} = "ldflags"

inherit go
GO_IMPORT = "cellgain.ddns.net/cellgain-public/nms-server"

SYSROOT_DIRS += "${bindir}"

do_compile() {
    GOPATH="${B}:${S}"
    export GOPATH
    PATH="${B}/bin:$PATH"
    export PATH

    # mender is using vendored dependencies, any 3rd party libraries go to into
    # /vendor directory inside mender source tree. In order for `go build` to pick
    # up vendored deps from our source tree, the mender source tree itself must be
    # located inside $GOPATH/src/${GO_IMPORT}
    #
    # recreate temporary $GOPATH/src/${GO_IMPORT} structure and link our source tree
    mkdir -p ${B}/src/$(dirname ${GO_IMPORT})
    test -e ${B}/src/${GO_IMPORT} || ln -s ${S} ${B}/src/${GO_IMPORT}
    cd ${B}/src/${GO_IMPORT}

    # run verbose build, we should see which dependencies are pulled in
    oe_runmake V=1 install
}

do_install() {
	install -d ${D}/${bindir}

	GOOS=$(eval $(${GO} env) && echo $GOOS)
	GOARCH=$(eval $(${GO} env) && echo $GOARCH)
	# mender is picked up from our fake GOPATH=${B}/bin; because go build is so
	# consistent, if it's a cross compilation build, binaries will be in
	# ${GOPATH}/bin/${GOOS}_${GOARCH}, howver if it's not, the binaries are in
	# ${GOPATH}/bin; handle cross compiled case only
	install -t ${D}/${bindir} -m 0755 ${B}/bin/${GOOS}_${GOARCH}/nms-server

	install -d ${D}/${systemd_unitdir}/system
	install -m 0644 ${WORKDIR}/nms-server.service ${D}/${systemd_unitdir}/system

	install -d ${D}/${localstatedir}/lib/nms-server

	install -d ${D}/${sysconfdir}/nms-server    
	install -m 0755 ${WORKDIR}/stop-ap.sh ${D}/${sysconfdir}/nms-server/

	install -d ${D}/${sysconfdir}/emmc    
	install -m 0755 ${WORKDIR}/emmc-install.sh ${D}/${sysconfdir}/emmc/
	install -m 0755 ${WORKDIR}/fw_env.config ${D}/${sysconfdir}/emmc/
	install -m 0644 ${WORKDIR}/img-yocto.sdimg.tar.gz ${D}/${sysconfdir}/emmc/

	install -d ${D}/data/nms-server/network
        install -m 0755 ${WORKDIR}/eth-conf.sh ${D}/data/nms-server/network
	install -m 0755 ${WORKDIR}/start-ap.sh ${D}/data/nms-server/network
	
	ln -s /data/nms-server/network/eth-conf.sh  ${D}/${sysconfdir}/nms-server/
	ln -s /data/nms-server/network/start-ap.sh  ${D}/${sysconfdir}/nms-server/

	install -d ${D}${sysconfdir}/udev/rules.d
	install -m 0644  ${WORKDIR}/70-custom-name.rules ${D}${sysconfdir}/udev/rules.d/70-custom-name.rules

	install -d ${D}/${systemd_system_unitdir}	
	if [ "${NMS_MODE}" = "remote" ]; then
        	bbplain "Compiling remote version"
		install -m 0755 ${WORKDIR}/nms-server-remote.service ${D}/${systemd_system_unitdir}/nms-server.service
    	else
		bberror "Compiling HEC version"
		install -m 0755 ${WORKDIR}/nms-server.service ${D}/${systemd_system_unitdir}/nms-server.service
	fi
	
}

