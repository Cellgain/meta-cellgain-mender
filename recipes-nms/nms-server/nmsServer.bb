DESCRIPTION = "NMS Server"
SRC_URI = "git://cellgain.ddns.net:30000/cellgain-public/nms-server.git;protocol=http;"
SRCREV = "${AUTOREV}"

inherit go
GO_IMPORT = "cellgain.ddns.net:30000/cellgain-public/nms-server"