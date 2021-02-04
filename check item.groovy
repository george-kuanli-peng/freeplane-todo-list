// @ExecutionModes({ON_SINGLE_NODE})

import java.text.SimpleDateFormat

def DUE_ICON = "emoji-23F0"

if (node.icons.contains("unchecked")) {
    node.icons.remove("unchecked")
    node.icons.remove(DUE_ICON)
    node.icons.add("checked")
    def checkDate = new Date()
    def dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm")
    node.putAt("check_date", dateFormat.format(checkDate))
}