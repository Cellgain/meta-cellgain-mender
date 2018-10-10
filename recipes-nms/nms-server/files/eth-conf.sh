#!/bin/bash

rm /etc/systemd/network/eth.network

cp /date/nms-server/eth.network /etc/systemd/network/eth.network

systemctl restart systemd-networkd