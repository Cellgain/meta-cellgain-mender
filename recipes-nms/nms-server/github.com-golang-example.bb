//github.com-golang-example.bb                                                                                                                           DESCRIPTION = "The Go Example Project"
SRC_URI = "git://github.com/golang/example.git;protocol=https;"
SRCREV = "${AUTOREV}"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${WORKDIR}/${PN}-${PV}/src/${GO_IMPORT}/LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"
DEPENDS += "golang.org-x"
inherit go
GO_IMPORT = "github.com/golang/example"