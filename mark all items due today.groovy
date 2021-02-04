// @ExecutionModes({ON_SINGLE_NODE})

import java.time.temporal.ChronoUnit
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId

def markCnt = 0
def DUE_ICON = "emoji-23F0"
LocalDateTime currTime = LocalDateTime.now()

for (it in c.findAll()) {

    if (!it.icons.contains("unchecked") || it.icons.contains(DUE_ICON)) continue

    try {
        def dueDate = LocalDateTime.ofInstant(
            it["due_date"].getDate().toInstant(),
            ZoneId.systemDefault()
        )
        if (!dueDate || dueDate.truncatedTo(ChronoUnit.DAYS).isAfter(currTime)) continue
    } catch (e) {
        continue
    }
    
    it.icons.add(DUE_ICON)
    markCnt++
}

c.statusInfo = "Marked " + markCnt + " items"