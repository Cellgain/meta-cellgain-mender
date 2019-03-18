DESCRIPTION = "Bootloader USB"

S = "${WORKDIR}/git"
B = "${WORKDIR}/build"

SRC_URI += " \
	git://cellgain.ddns.net:30000/cellgain-public/bootloader-usb.git;protocol=http; \
	"
SRCREV = "${AUTOREV}"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

DEPENDS += "	go-cross-${TARGET_ARCH} \	   
		libusb1 \
		pkgconfig \
	"
RDEPENDS_${PN} = "bash"

# Go binaries produce unexpected effects that the Yocto QA mechanism doesn't
# like. We disable those checks here.
INSANE_SKIP_${PN} = "ldflags"

inherit go systemd pkgconfig
GO_IMPORT = "cellgain.ddns.net/cellgain-public/bootloader-usb"

SYSROOT_DIRS += "${bindir}"

do_compile() {
    GOPATH="${B}:${S}"
    export GOPATH
    PATH="${B}/bin:$PATH"
    export PATH

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
	
	install -t ${D}/${bindir} -m 0755 ${B}/bin/${GOOS}_${GOARCH}/bootloader-usb
	install -d ${D}/${localstatedir}/lib/bootloader-usb
}

