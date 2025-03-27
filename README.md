# Quest For The Spirit Stick - Madison C.S. Project

This is a multi-year project created by Madison High School students.  
Below is a list of the changes and improvements made each year.

## 2024-2025 Updates

### Bug Fixes

1. **White Fireball Attack Strength Bug**

    - **Issue:** Selecting the White Fireball changed the attack strength of everything, not just magic.
    - **Fix:** Commented out the code that was setting the attack variable.
    - **Done by:** Gavin

2. **Black Screen Crash**

    - **Issue:** Walking into a certain spot caused the screen to go black and crash.
    - **Fix:** The event handler was transporting the player to a non-existent location/map. Adjusted the destination.
    - **Done by:** Rik

3. **Movement Priority Issue**

    - **Issue:** Left took priority over right, and up took priority over down. Needed to prioritize the most recent key press.
    - **Fix:** Adjusted movement handling logic.
    - **Done by:** Bryan

4. **Overlapping Trees on Path to Shop**

    - **Issue:** Some trees were placed on top of each other.
    - **Fix:** Adjusted tree placement during map generation.
    - **Done by:** Rik

5. **Monsters Not Dying Properly**

    - **Issue:** Some monsters would remain still and require being defeated twice.
    - **Fix:** Same issue as overlapping trees; adjusted placement logic.
    - **Done by:** Rik

6. **Enter Key Skipping First Dialogue**

    - **Issue:** Pressing enter before talking or opening a chest skipped the first dialogue line.
    - **Fix:** Adjusted key handler to ensure enter is only processed when actively pressed.
    - **Done by:** Bryan

7. **Shop Crash When Selecting an Empty Slot**

    - **Issue:** Game crashed when attempting to buy an empty slot in the shop.
    - **Fix:** Added an if statement to ensure the selection remains within the valid array bounds.
    - **Done by:** Rik

8. **Immortal Mode Health Bug**
    - **Issue:** Health continued to decrease into negatives in Immortal Mode.
    - **Fix:** Added an if statement in the Player and Entity classes to prevent health reduction in this mode.
    - **Done by:** Rik

### New Features

No new features have been added yet this year.

## Potential Additions, Improvements, and Bugs

-   Add keybinds menu
-   Support for WASD movement in addition to arrow keys
-   Do not allow projectiles and other objects to move through doorways, trees, and other objects.
-   Pause button can be held down
-   Add multiple save slots
-   Hide "Load Game" until you have first created a "New Game". Also move "Load Game" above "New Game" in the menu

## Contributors

This is a list of students who have contributed to the project each year.

### 2024-2025

-   Rik
-   Bryan
-   Gavin
-   Matthew
