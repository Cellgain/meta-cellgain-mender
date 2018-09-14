FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += " file://wpa_supplicant-wlan0.conf"

FILES_${PN} += "${sysconfdir}/wpa_supplicant/wpa_supplicant-wlan0.conf"

CELLGAIN_WIFI_SSID ?= "Fibertel WiFi631 2.4GHz"
CELLGAIN_WIFI_PASSWORD ?= "0049573606"

do_install_append() {

  install -d ${D}${sysconfdir}/wpa_supplicant

  sed -i -e 's#[@]CELLGAIN_WIFI_PASSWORD[@]#${CELLGAIN_WIFI_PASSWORD}#' ${WORKDIR}/wpa_supplicant-wlan0.conf
	sed -i -e 's#[@]CELLGAIN_WIFI_SSID[@]#${CELLGAIN_WIFI_SSID}#' ${WORKDIR}/wpa_supplicant-wlan0.conf

	install -m 0755 ${WORKDIR}/wpa_supplicant-wlan0.conf ${D}${sysconfdir}/wpa_supplicant/wpa_supplicant-nl80211-wlan0.conf

  # Make sure the system directory for systemd exists.
  install -d ${D}${sysconfdir}/systemd/system/multi-user.target.wants/

  # Link the service file for autostart.
  ln -s ${systemd_unitdir}/system/wpa_supplicant-nl80211@.service \
  ${D}${sysconfdir}/systemd/system/multi-user.target.wants/wpa_supplicant-nl80211@wlan0.service

}
