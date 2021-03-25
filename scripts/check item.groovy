// @ExecutionModes({ON_SINGLE_NODE})

import java.text.SimpleDateFormat

if (node.icons.contains("unchecked")) {
    node.icons.remove("unchecked")
    node.icons.add("checked")
    node.reminder.remove()
    def checkDate = new Date()
    def dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm")
    node.putAt("check_date", dateFormat.format(checkDate))
}
