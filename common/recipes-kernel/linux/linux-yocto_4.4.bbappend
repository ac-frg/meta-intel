FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

LINUX_VERSION_INTEL_COMMON = "4.4.3"
SRCREV_META_INTEL_COMMON = "082c2ea9f6bffa9b2bfa8f1e10b88046b8f064fd"
SRCREV_MACHINE_INTEL_COMMON = "7a073202d4d47b5ffecf1e8f57b036f83b1fe2e0"

KBRANCH_INTEL_COMMON = "standard/base"
KERNEL_FEATURES_INTEL_COMMON += "features/amt/mei/mei.scc"

LINUX_VERSION_core2-32-intel-common = "${LINUX_VERSION_INTEL_COMMON}"
COMPATIBLE_MACHINE_core2-32-intel-common = "${MACHINE}"
KMACHINE_core2-32-intel-common = "intel-core2-32"
KBRANCH_core2-32-intel-common = "${KBRANCH_INTEL_COMMON}"
SRCREV_meta_core2-32-intel-common ?= "${SRCREV_META_INTEL_COMMON}"
SRCREV_machine_core2-32-intel-common ?= "${SRCREV_MACHINE_INTEL_COMMON}"
KERNEL_FEATURES_append_core2-32-intel-common = "${KERNEL_FEATURES_INTEL_COMMON}"

LINUX_VERSION_corei7-64-intel-common = "${LINUX_VERSION_INTEL_COMMON}"
COMPATIBLE_MACHINE_corei7-64-intel-common = "${MACHINE}"
KMACHINE_corei7-64-intel-common = "intel-corei7-64"
KBRANCH_corei7-64-intel-common = "${KBRANCH_INTEL_COMMON}"
SRCREV_meta_corei7-64-intel-common ?= "${SRCREV_META_INTEL_COMMON}"
SRCREV_machine_corei7-64-intel-common ?= "${SRCREV_MACHINE_INTEL_COMMON}"
KERNEL_FEATURES_append_corei7-64-intel-common = "${KERNEL_FEATURES_INTEL_COMMON}"

# Quark / X1000 BSP Info
LINUX_VERSION_i586-nlp-32-intel-common = "${LINUX_VERSION_INTEL_COMMON}"
COMPATIBLE_MACHINE_i586-nlp-32-intel-common = "${MACHINE}"
KMACHINE_i586-nlp-32-intel-common = "intel-quark"
KBRANCH_i586-nlp-32-intel-common = "${KBRANCH_INTEL_COMMON}"
SRCREV_meta_i586-nlp-32-intel-common ?= "${SRCREV_META_INTEL_COMMON}"
SRCREV_machine_i586-nlp-32-intel-common ?= "${SRCREV_MACHINE_INTEL_COMMON}"
KERNEL_FEATURES_append_i586-nlp-32-intel-common = ""


# For Crystalforest and Romley
KERNEL_MODULE_AUTOLOAD_append_core2-32-intel-common = " uio"
KERNEL_MODULE_AUTOLOAD_append_corei7-64-intel-common = " uio"

# For FRI2, NUC
KERNEL_MODULE_AUTOLOAD_append_core2-32-intel-common = " iwlwifi"
KERNEL_MODULE_AUTOLOAD_append_corei7-64-intel-common = " iwlwifi"