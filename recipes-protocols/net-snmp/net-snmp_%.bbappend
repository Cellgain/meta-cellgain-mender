FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

do_install_append(){	
	install -m 755 ${WORKDIR}/snmpd.conf ${D}${sysconfdir}/snmp/
}
