// @ExecutionModes({ON_SINGLE_NODE})

def archiveCnt = 0

c.findAll().each {

    if (!it.icons.contains("checked")) return

    if (it.isRoot()) return

    try {
        it.at("..'[archive]'")
        return
    } catch (IllegalArgumentException e) {}

    def archiveNode = null
    try {
        archiveNode = it.at("../'[archive]'")
    } catch (IllegalArgumentException e) {
        archiveNode = it.getParent().createChild("[archive]")
    }

    it.moveTo(archiveNode)
    archiveCnt++
    archiveNode.setFolded(true)
}

c.statusInfo = "Archived " + archiveCnt + " items"