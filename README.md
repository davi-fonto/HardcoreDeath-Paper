HardcoreDeath
=============

Paper 1.21.8 plugin that deletes a player's inventory on death in configured worlds.

Installation
- Build with Maven: mvn clean package
- Drop the resulting JAR into your server's plugins folder

Configuration
Edit `config.yml` and add world names under `enabled-worlds`.
Example:

enabled-worlds:
  - world
  - world_nether

Behavior
When a player dies in a configured world, their dropped items are cleared and inventory is cleared (items are not dropped to the ground).
