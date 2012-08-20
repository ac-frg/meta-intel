SUMMARY = "Cedartrail PowerVR Graphics Driver version 1.0.2 binaries"
DESCRIPTION = "2D, 3D and Media user space driver for Cedartrail platform \
The binaries are covered by the Intel Free Distribution Binary License. \ 
The user must make himself/herself aware of the Licensing terms \
before enabling build of the Cedartrail PowerVR Graphics Driver via \
this recipe.  Please see the README in meta-cedartrail for instructions \
for enabling the build of the driver "
 
LICENSE_FLAGS = "license_${PN}_${PV}"
LICENSE = "Intel Free Distribution Binary License"
LIC_FILES_CHKSUM = " \
    file://${S}/usr/share/doc/psb-video-cdv-${PSB-VIDEO-REV}/license.txt;md5=b14d99f8d4ed664e9ce95057f0bb5b65  \
    file://${S}/usr/share/doc/pvr-bin-cdv-${PVR-BIN-REV_N}/license.txt;md5=b14d99f8d4ed664e9ce95057f0bb5b65"

DEPENDS = "rpm-native libva"

PR = "r0"

PSB-VIDEO = "psb-video-cdv-0.17-2.1.i586.rpm"
PSB-VIDEO-REV = "0.17"

PVR-BIN = "pvr-bin-cdv-1.7.862890_05-1.1.i586.rpm"
PVR-BIN-REV = "1.7.862890"
PVR-BIN-REV_N = "1.7.862890_05"

LIBWSBM = "libwsbm-cdv-1.1.0-3.1.i586.rpm"


NON-OSS-PATH = "http://repo.meego.com/MeeGo/builds/1.2.0/1.2.0.10.1.20120723.1/repos/non-oss/ia32/packages/"
OSS-PATH =     "http://repo.meego.com/MeeGo/updates/1.2.0/repos/oss/ia32/packages/"


SRC_URI = "${NON-OSS-PATH}${PSB-VIDEO};name=psbrpm \
	   ${NON-OSS-PATH}${PVR-BIN};name=pvrrpm \
	   ${OSS-PATH}${LIBWSBM};name=wsbmrpm \
		"
SRC_URI[pvrrpm.md5sum] = "92dbb85a259dff73c6e4de68d158ef10"
SRC_URI[pvrrpm.sha256sum] = "46356021efa990cde367b2cdec8626db8c1457234771c6459a106b8342c549c1"

SRC_URI[psbrpm.md5sum] =  "0300f7485306bb039ee33e6238a00ae3"
SRC_URI[psbrpm.sha256sum] = "8e71f2ff7464b20823d7f552729d39cb9e3d75badc9b206d43bcc2429849f6c0"

SRC_URI[wsbmrpm.md5sum] = "b8b21ca8325abd7850d197f9bf3071c7"
SRC_URI[wsbmrpm.sha256sum] = "f436386967c1adec5211e662251bd542bbe0b8cd55e1d9f9c203da5ee934d4f0"

S  = "${WORKDIR}/cdv-graphics-drivers_${PV}"

FILES_${PN} += "${libdir}/dri ${libdir}/pvr/cdv/dri ${libdir}/pvr/cdv ${libdir}/xorg/modules/drivers"

FILES_${PN} += "${base_libdir}/firmware"
FILES_${PN} += "${sysconfdir}/X11/xorg.conf.d"

FILES_${PN} += "${libdir}/lib*.so"

FILES_${PN} += "${libdir}/pvr/cdv/xorg/modules/drivers"

FILES_${PN} += "${datadir}/doc/psb-video-cdv-${PSB-VIDEO-REV}/license.txt"
FILES_${PN} += "${datadir}/doc/pvr-bin-cdv-${PVR-BIN-REV_N}/license.txt"


TARGET_CC_ARCH += "${CFLAGS}{LDFLAGS}"
INSANE_SKIP_${PN} += "ldflags"
INSANE_SKIP_${PN}-dbg += "ldflags"

do_configure () {

# Extract  license files from rpms
rpm2cpio.sh ${WORKDIR}/${PSB-VIDEO} |cpio -ivd ./usr/share/doc/psb-video-cdv-${PSB-VIDEO-REV}/license.txt
rpm2cpio.sh ${WORKDIR}/${PVR-BIN} |cpio -ivd ./usr/share/doc/pvr-bin-cdv-${PVR-BIN-REV_N}/license.txt

}

do_install() {
	
	mv ${WORKDIR}/*.rpm  ${S}	
				
	rpm2cpio.sh ${S}/${LIBWSBM} | cpio -id
	
	install -d -m 0755                                    ${D}${libdir}/dri

	install -m 0755 ${S}/usr/lib/*                        ${D}${libdir}/

	rpm2cpio.sh ${S}/${PSB-VIDEO} | cpio -id

	install -d -m 0755				      ${D}${base_libdir}/firmware

	install -m 0755 ${S}/usr/lib/dri/*     		      ${D}${libdir}/dri/

	install -m 0755 ${S}/lib/firmware/*		      ${D}${base_libdir}/firmware

	rpm2cpio.sh  ${S}/${PVR-BIN}  | cpio -id

	install -d -m 0755                                    ${D}${libdir}/pvr/cdv/dri

	install -m 0755 ${S}/usr/lib/pvr/cdv/dri/*            ${D}${libdir}/pvr/cdv/dri

	install -d -m 0755                                    ${D}${sysconfdir}/X11/xorg.conf.d
	install -m 0755 ${S}/etc/powervr.ini		      ${D}${sysconfdir}/	
	install -m 0755 ${S}/etc/X11/xorg.conf.d/*            ${D}${sysconfdir}/X11/xorg.conf.d/
	install -m 0755 ${S}/usr/lib/dri/pvr_dri.so    	      ${D}${libdir}/dri/
	install -m 0755 ${S}/usr/lib/*.so.*                   ${D}${libdir}/    

	
	install -m 0755 ${S}/usr/lib/libegl4ogl.so.${PVR-BIN-REV}   	${D}${libdir}/libegl4ogl.so
	install -m 0755 ${S}/usr/lib/libEGL.so.${PVR-BIN-REV}  		${D}${libdir}/libEGL.so
	install -m 0755 ${S}/usr/lib/libGLES_CM.so.${PVR-BIN-REV} 	${D}${libdir}/libGLES_CM.so
	install -m 0755 ${S}/usr/lib/libGLES_CM.so.${PVR-BIN-REV}  	${D}${libdir}/libGLESv1_CM.so
	install -m 0755 ${S}/usr/lib/libGLESv2.so.${PVR-BIN-REV}  	${D}${libdir}/libGLESv2.so
	install -m 0755 ${S}/usr/lib/libglslcompiler.so.${PVR-BIN-REV} 	${D}${libdir}/libglslcompiler.so
	install -m 0755 ${S}/usr/lib/libIMGegl.so.${PVR-BIN-REV}  	${D}${libdir}/libIMGegl.so
	install -m 0755 ${S}/usr/lib/libOpenVG.so.${PVR-BIN-REV} 	${D}${libdir}/libOpenVG.so
	install -m 0755 ${S}/usr/lib/libOpenVGU.so.${PVR-BIN-REV} 	${D}${libdir}/libOpenVGU.so
	install -m 0755 ${S}/usr/lib/libpvr2d.so.${PVR-BIN-REV}  	${D}${libdir}/libpvr2d.so
	install -m 0755 ${S}/usr/lib/libPVROGL_MESA.so.${PVR-BIN-REV} 	${D}${libdir}/libPVROGL_MESA.so
	install -m 0755 ${S}/usr/lib/libpvrPVR2D_BLITWSEGL.so.${PVR-BIN-REV} 	${D}${libdir}/libpvrPVR2D_BLITWSEGL.so
	install -m 0755 ${S}/usr/lib/libpvrPVR2D_DRIWSEGL.so.${PVR-BIN-REV} 	${D}${libdir}/libpvrPVR2D_DRIWSEGL.so
	install -m 0755 ${S}/usr/lib/libpvrPVR2D_FLIPWSEGL.so.${PVR-BIN-REV}  	${D}${libdir}/libpvrPVR2D_FLIPWSEGL.so
	install -m 0755 ${S}/usr/lib/libpvrPVR2D_LINUXFBWSEGL.so.${PVR-BIN-REV}	${D}${libdir}/libpvrPVR2D_LINUXFBWSEGL.so
	install -m 0755 ${S}/usr/lib/libPVRScopeServices.so.${PVR-BIN-REV}  	${D}${libdir}/libPVRScopeServices.so
	install -m 0755 ${S}/usr/lib/libsrv_init.so.${PVR-BIN-REV}  	${D}${libdir}/libsrv_init.so
	install -m 0755 ${S}/usr/lib/libsrv_um.so.${PVR-BIN-REV} 	${D}${libdir}/libsrv_um.so
	install -m 0755 ${S}/usr/lib/libusc.so.${PVR-BIN-REV} 		${D}${libdir}/libusc.so

	install -m 0755 ${S}/usr/lib/pvr/cdv/*.so.*           		${D}${libdir}/pvr/cdv/    

	install -d -m 0755 ${D}${libdir}/pvr/cdv/xorg/modules/drivers
	install -m 0755 ${S}/usr/lib/pvr/cdv/xorg/modules/drivers/* 	${D}${libdir}/pvr/cdv/xorg/modules/drivers/

    	install -d -m 0755                                    		${D}${libdir}/xorg/modules/drivers
   
	install -m 0755 ${S}/usr/lib/xorg/modules/drivers/*   		${D}${libdir}/xorg/modules/drivers/
   
	install -d -m 0755 ${D}${datadir}/doc/psb-video-cdv-${PSB-VIDEO-REV}
	install -d -m 0755 ${D}${datadir}/doc/pvr-bin-cdv-${PVR-BIN-REV_N}

	install -m 0755 ${S}/usr/share/doc/psb-video-cdv-${PSB-VIDEO-REV}/license.txt ${D}${datadir}/doc/psb-video-cdv-${PSB-VIDEO-REV}/license.txt
	install -m 0755 ${S}/usr/share/doc/pvr-bin-cdv-${PVR-BIN-REV_N}/license.txt	${D}${datadir}/doc/pvr-bin-cdv-${PVR-BIN-REV_N}/license.txt

}