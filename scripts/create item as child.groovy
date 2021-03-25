// @ExecutionModes({ON_SINGLE_NODE})

import java.text.SimpleDateFormat
import javax.swing.*

// from gist by [wytten](https://gist.github.com/wytten/5213495)
def prompt = {
    JFrame jframe = new JFrame()
    String answer = JOptionPane.showInputDialog(jframe, it)
    jframe.dispose()
    answer
}

def getArchiveNodePos = {
    try {
        def archiveNode = it.at("../'[archive]'")
        return it.getParent().getChildPosition(archiveNode)
    } catch (e) {
        return -1
    }
}

// from example by [Matt Ball](https://stackoverflow.com/questions/4024544/how-to-parse-dates-in-multiple-formats-using-simpledateformat)
def tryParseDate = {
    List<String> formatStrs = Arrays.asList("y-M-d H:m", "y/M/d H:m", "y-M-d", "y/M/d")
    for (fmt in formatStrs) {
        try {
            return new SimpleDateFormat(fmt).parse(it)
        } catch (e) {
        }
    }
    return null
}

def itemText = prompt("Enter text for item:")
if (!itemText) return

itemNode = node.createChild(itemText)
itemNode.icons.add("unchecked")
// move the item right before the archive node if there is any
def archivePos = getArchiveNodePos(itemNode)
if (archivePos >= 0) {
    itemNode.moveTo(itemNode.getParent(), archivePos)
}

def reminderDate = prompt("Enter reminder date (leave blank if to skip):\n\n" +
                     "Examples:\n" +
                     "2021/5/18\n2021/5/18 17:30\n2021-5-18\n2021-5-18 17:30")
if (reminderDate) reminderDate = tryParseDate(reminderDate)
if (reminderDate) {
    itemNode.reminder.createOrReplace(reminderDate, "HOUR", 1)
}
