//golang.org-x.bb
DESCRIPTION = "Go X libraries"
SRC_URI = "\
         git://github.com/golang/net.git;protocol=https;name=net;destsuffix=${PN}-${PV}/src/golang.org/x/net \
         git://github.com/golang/text.git;protocol=https;name=text;destsuffix=${PN}-${PV}/src/golang.org/x/text \
         git://github.com/golang/tools.git;protocol=https;name=tools;destsuffix=${PN}-${PV}/src/golang.org/x/tools \
         git://github.com/golang/crypto.git;protocol=https;name=crypto;destsuffix=${PN}-${PV}/src/golang.org/x/crypto \
         git://github.com/golang/sys.git;protocol=https;name=sys;destsuffix=${PN}-${PV}/src/golang.org/x/sys \
         "
 SRCREV_text = "${AUTOREV}"
 SRCREV_net = "${AUTOREV}"
 SRCREV_crypto = "${AUTOREV}"
 SRCREV_tools = "${AUTOREV}"
 SRCREV_sys = "${AUTOREV}"
LICENSE = "MIT"
 LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
inherit go
 GO_IMPORT = "golang.org/x"