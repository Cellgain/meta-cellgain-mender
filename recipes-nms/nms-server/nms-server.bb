DESCRIPTION = "NMS Server"
SRC_URI = "git://cellgain.ddns.net:30000/cellgain-public/nms-server.git;protocol=http;"
SRCREV = "${AUTOREV}"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

DEPENDS = "go-cross-${TARGET_ARCH}"

S = "${WORKDIR}"

# Go binaries produce unexpected effects that the Yocto QA mechanism doesn't
# like. We disable those checks here.
INSANE_SKIP_${PN} = "ldflags"

inherit go
GO_IMPORT = "cellgain.ddns.net:30000/cellgain-public/nms-server"

do_compile() {
  
}

do_install() {
  install -d "${D}/${bindir}"
  install -m 0755 "${S}/nms-server" "${D}/${bindir}"
}