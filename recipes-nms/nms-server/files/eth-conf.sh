#!/bin/bash

sudo rm /etc/systemd/network/bridge.network > /dev/null 2>&1

sudo ln -s /data/nms-server/network/bridge.network /etc/systemd/network/bridge.network > /dev/null 2>&1

sudo systemctl restart systemd-networkd > /dev/null 2>&1

sleep 5

GATEWAY=$(/sbin/ip route | awk '/default/ { print $3 }')

if ping -q -c 1 -W 1 $GATEWAY >/dev/null; then
	connected=true
else
	connected=false
fi

echo $connected
