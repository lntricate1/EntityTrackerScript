# EntityTrackerScript
Entity Tracker scarpet script, for visualizing accurate sub-tick entity movement and explosion behaviors.
NOTE: You need to use this carpet fork for it to work. It is the latest 1.16.5 version with 2 extra injection into the setPos() method in Entity.class. If they accept my [pull request](https://github.com/gnembon/fabric-carpet/pull/1096), it will no longer be necessary.
If you find bugs ping me in the [tnt archive](https://discord.gg/vPyUBcdmZV).

![2021-09-07_03 51 06](https://user-images.githubusercontent.com/29168747/132648895-3aeceba9-18a9-4cd1-a0a0-ead5e020513a.png)

![2021-09-07_21 35 16](https://user-images.githubusercontent.com/29168747/132648957-d0e94cbf-4e7e-4df6-b4a2-662cba796fa3.png)

## Instructions
1. Stick the carpet fork in your mods, replacing carpet 1.16.5
2. Stick the script in your .minecraft/config/carpet/scripts, or in the scripts folder of a particular save
3. In the world, run /script load entitytracker

## Commands
### /entitytracker list
Lists all active trackers, along with their indices in case you decide to remove them.

### /entitytracker clear [index]
If run with no index, clears all trackers.
If run with an index, removes that specific tracker.
Note: Indices are not in order of creation. Scarpet maps are unordered. Keep in mind that they might shuffle around if you remove a tracker.

### /entitytracker help
Prints a help menu.

### /entitytracker points \<sizex> \<sizey> \<eyes?> \<duration> \<max count> \<radius> \<entity selector>
Draws a cube at each position the entities tick, centered with its bottom face at the entity feet.
Both sizex and sizey can be set to 'auto', which sets the size dynamically depending on the size of the entity.
Duration is in ticks, and radius is in blocks.

### /entitytracker lines \<accurate/motion/straight> \<duration> \<max count> \<radius> \<entity selector>
Draws lines.
- In accurate mode, it draws the per-axis collision checks that entities actually do. Keep in mind this is not how projectiles behave, because they use raycasts instead.
- In straight mode, it draws a straight line from position to position. Keep in mind this is only accurate for projectiles, since every other entity moves like accurate mode.
- In motion mode, it draws a line from the entity's feet representing the motion vector.

### /entitytracker labels fuse \<duration> \<max count> \<radius>
### /entitytracker labels lifetime \<duration> \<max count> \<radius> \<entity selector>
### /entitytracker labels \<motion/position> \<precision> \<duration> \<max count> \<radius> \<entity selector>
Displays text.
- In fuse mode, it displays the fuse time of the tnt in ticks.
- In lifetime mode, it displays the age of the entity in ticks.
- In motion/position modes, it displays the numerical representation of the motion and position vectors, respectively.

### /entitytracker explosions points \<duration> \<limit> \<radius>
### /entitytracker explosions \<rays/applied_velocity> \<duration> \<limit> \<radius> \<entity selection>
Draws explosions.
- In points mode, it draws a cube at the explosion. The cube is centered with the centroid at the explosion.
- In rays mode, it draws the rays used for calculating exposure, only to the entities specified.
- In applied_velocity mode, it draws the direction vector of motion applied to the entity, scaled proportionally and multiplied by 10. It does not factor in exposure.
