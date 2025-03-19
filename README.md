# 2D-Adventure
Madison AP CS Class Project


# Madison C.S. Project  "Quests For The Spirit Stick"

This is a multi year project created by Madison highschool students.

# 2024-2025
This year they decided to start with some bug fixes the list of fixes they chose are below.
1. When White fire ball is selected it changes the attack strenght of everything not just a magic.   we need to separate the magic attack and the weapon attack  ------ Figured out we just needed to comment out the code for seting the attack variable. ---done Gavin 02.2025

2. walk into a spot on the screen and the screen goes black and crashes. --- In the event handler it was taking us to a location and map that did not exist. --done Rik 02.2025 

3. movement left/right and up/down left takes preference over right. Up takes preference over down and up down take preference over left right.   we need to make the most recent key the preference. FIXED 03/09/2025 see log 03.2025 Bryan

4. path to shop some trees on top of each other. ----- Just needed to adjust creation of tree locations -- 02.2025 done

5. some monsters are still sit there and then don't die until you kill them twice. ---  same as nubmer 4 but monsters -- 02.2025 done

6. if press enter before talking or opening a chest it skips first dialogue..--- changed key handler to always make enter false expect when pushed.  02.2025 Bryan  -- done 

7. Game Crashes when buying somthing in the shop if select empty space.  Added if statement to make sure the arraylist was not out of bounds.  done 03/09/2025

8. Do not allow objects to move through doorway and maybe trees. 

9. imortal mode health in imortal mode your health continues to decrease into the negatives.  make so that you do not decrease health at all in go mode.  ------ needed to added if statement to the player class and the entity class. around losing life. - 02.2025 Done

# Potential additions
1. key bines menu
2. WASD key instead of arrow

