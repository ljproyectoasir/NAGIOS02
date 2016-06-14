TARGETS = mountkernfs.sh udev mountdevsubfs.sh bootlogd hostname.sh lvm2 mountall.sh mountall-bootclean.sh mountnfs.sh mountnfs-bootclean.sh networking rpcbind urandom checkfs.sh checkroot.sh procps stop-bootlogd-single bootmisc.sh udev-finish checkroot-bootclean.sh x11-common kmod
INTERACTIVE = udev checkfs.sh checkroot.sh
udev: mountkernfs.sh
mountdevsubfs.sh: mountkernfs.sh udev
bootlogd: mountdevsubfs.sh
hostname.sh: bootlogd
lvm2: bootlogd mountdevsubfs.sh udev
mountall.sh: checkfs.sh checkroot-bootclean.sh lvm2
mountall-bootclean.sh: mountall.sh
mountnfs.sh: mountall.sh mountall-bootclean.sh networking rpcbind
mountnfs-bootclean.sh: mountall.sh mountall-bootclean.sh mountnfs.sh
networking: mountkernfs.sh mountall.sh mountall-bootclean.sh urandom procps
rpcbind: networking mountall.sh mountall-bootclean.sh
urandom: mountall.sh mountall-bootclean.sh
checkfs.sh: checkroot.sh lvm2
checkroot.sh: mountdevsubfs.sh hostname.sh bootlogd
procps: mountkernfs.sh mountall.sh mountall-bootclean.sh udev bootlogd
stop-bootlogd-single: mountall.sh mountall-bootclean.sh udev mountnfs.sh mountnfs-bootclean.sh networking rpcbind mountkernfs.sh urandom hostname.sh checkfs.sh checkroot.sh procps bootmisc.sh udev-finish bootlogd mountdevsubfs.sh lvm2 checkroot-bootclean.sh x11-common kmod
bootmisc.sh: mountall-bootclean.sh mountnfs-bootclean.sh mountall.sh mountnfs.sh udev checkroot-bootclean.sh
udev-finish: udev mountall.sh mountall-bootclean.sh
checkroot-bootclean.sh: checkroot.sh
x11-common: mountall.sh mountall-bootclean.sh mountnfs.sh mountnfs-bootclean.sh
kmod: checkroot.sh
