PACKAGECONFIG_append = " udev"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += " \
	file://70-usb-scale.rules \
"


FILES_${PN} += " \
	${D}${sysconfdir}/udev/rules.d/70-usb-scale.rules \
"


do_install_append() {
	install -d ${D}${sysconfdir}/udev/rules.d/
	install -m 0644 ${D}${sysconfdir}/udev/rules.d/70-usb-scale.rules
}
