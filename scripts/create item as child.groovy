// @ExecutionModes({ON_SINGLE_NODE})
// prompt from gist by [wytten](https://gist.github.com/wytten/5213495)

import javax.swing.*

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

def itemText = prompt("Enter text for item:")
if (!itemText) return

itemNode = node.createChild(itemText)
itemNode.icons.add("unchecked")
// move the item right before the archive node if there is any
def archivePos = getArchiveNodePos(itemNode)
if (archivePos >= 0) {
    itemNode.moveTo(itemNode.getParent(), archivePos)
}

def dueDate = prompt("Enter due date (leave blank if no due date):")
if (dueDate) {
    itemNode.putAt("due_date", dueDate)
}