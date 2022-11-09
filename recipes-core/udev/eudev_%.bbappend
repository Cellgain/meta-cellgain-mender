PACKAGECONFIG:append = " eudev"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += " \
	file://70-usb-scale.rules \
"


FILES_${PN} += " \
	${sysconfdir}/udev/rules.d/70-usb-scale.rules \
"


do_install:append() {
	install -d ${D}${sysconfdir}/udev/rules.d
	install -m 0644  ${WORKDIR}/70-usb-scale.rules ${D}${sysconfdir}/udev/rules.d/70-usb-scale.rules
}

