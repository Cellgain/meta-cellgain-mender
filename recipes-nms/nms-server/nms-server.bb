DESCRIPTION = "NMS Server"
SRC_URI = "git://cellgain.ddns.net:30000/cellgain-public/nms-server.git;protocol=http;"
SRCREV = "${AUTOREV}"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
inherit go
GO_IMPORT = "cellgain.ddns.net:30000/cellgain-public/nms-server"
