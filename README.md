# Quest For The Spirit Stick - Madison C.S. Project
**This is a multi-year, collaborative project created by Madison High School Computer Science students.**  

**The project is a fantasy adventure game written in java to explore skills like java programming, game design, code management, teamwork, and planning.**  

**Below is a list of all changes and contributions made to the project each school year.**  


## 2024-2025
### New Features
No new features have been added yet this year.


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


### Other Changes and Contributions
1. **Published to GitHub**
	- **Change:** Published this codebase to GitHub for easy access and control by all future contributers
	- **Done by:** Rik, Matthew

2. **README.md Created**
	- **Change:** Created a new README.md designed to be easy to extend to future years and track contributions
	- **Done by:** Matthew

3. **GameState Class Seperation**
	- **Change:** Moved each gameState handling from KeyHandler.java to their own seperate file in the state folder
	- **Done by:** Rik, Matthew

4. **Codebase Cleanup Work**
	- **Change:** Formatted, removed, and made small changes to a number of files to improve readability
	- **Done by:** Matthew

### Contributors
- Rik
- Bryan
- Gavin
- Matthew


## Pre 2024
This project was published to GitHub in the 2024-2025 school year.  
Special thanks to everyone who worked on this project prior to 2024.