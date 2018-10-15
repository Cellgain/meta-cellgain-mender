PACKAGECONFIG_append = " networkd resolved"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += " \
	file://eth.network \
	file://enp.network \
	file://wireless.network \
	file://bridge.netdev \
	file://bridge.network \
"


FILES_${PN} += " \
	/data/nms-server/network/eth.network \
	/data/nms-server/network/enp.network \
	/data/nms-server/network/wireless.network \
	/data/nms-server/network/bridge.netdev \
	/data/nms-server/network/bridge.network \
"


do_install_append() {
  if ${@bb.utils.contains('PACKAGECONFIG','networkd','true','false',d)}; then
        install -d ${D}/data/nms-server/network
        install -m 0644 ${WORKDIR}/eth.network ${D}/data/nms-server/network
        install -m 0644 ${WORKDIR}/enp.network ${D}/data/nms-server/network
        install -m 0644 ${WORKDIR}/wireless.network ${D}/data/nms-server/network
        install -m 0644 ${WORKDIR}/bridge.netdev ${D}/data/nms-server/network
        install -m 0644 ${WORKDIR}/bridge.network ${D}/data/nms-server/network

	ln -s /data/nms-server/network/wireless.network  ${D}${sysconfdir}/systemd/network/25-wireless.network
	ln -s /data/nms-server/network/enp.network  ${D}${sysconfdir}/systemd/network/25-enp.network
	ln -s /data/nms-server/network/eth.network  ${D}${sysconfdir}/systemd/network/20-eth.network
	ln -s /data/nms-server/network/bridge.netdev  ${D}${sysconfdir}/systemd/network/bridge.netdev
	ln -s /data/nms-server/network/bridge.network  ${D}${sysconfdir}/systemd/network/25-bridge.network
  fi
}
