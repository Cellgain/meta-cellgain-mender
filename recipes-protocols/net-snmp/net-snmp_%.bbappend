FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

do_install() {	
	install -m 755 ${WORKDIR}/snmpd.conf ${D}${sysconfdir}/snmp/
}
