#!/bin/bash

sudo rm /etc/systemd/network/eth.network > /dev/null 2>&1

sudo ln -s /data/nms-server/network/eth.network /etc/systemd/network/eth.network > /dev/null 2>&1

sudo systemctl restart systemd-networkd > /dev/null 2>&1

sleep 5

GATEWAY=$(/sbin/ip route | grep eth0 | awk '/default/ { print $3 }')

if ping -q -c 1 -W 1 -I eth0 $GATEWAY >/dev/null; then
	connected=true
else
	connected=false
fi

echo $connected
