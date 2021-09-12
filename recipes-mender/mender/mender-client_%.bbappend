FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI_append = " file://mender.conf file://server.crt"

MENDER_UPDATE_POLL_INTERVAL_SECONDS = "60"
MENDER_INVENTORY_POLL_INTERVAL_SECONDS = "60"
MENDER_SERVER_URL = "https://mender-cellgain.ddns.net"
