FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

do_install:append() {	
	install -m 755 ${WORKDIR}/snmpd.conf ${D}${sysconfdir}/snmp/
}
