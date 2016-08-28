# EvolutionGame
2D life simulation. An 2D array is populated with creatures, predators, food and poison mushrooms. Idealy the creatures will eat food, and avoid
predators and mushrooms, but this behaviour has to be learned through many generations of evolution. When a generation ends (Triggered when the population drops below a certain threshold)
a new generation of creatures is created by combining the chromosomes (that define a creatures reponse to other entities in the world) of the surviving
creatures with the highest energy levels. Through successive combination and mutation of chromosomes the creatures will learn how best to survive 
for extended periods of time.

The GUI provides controls to manually step through the simulation, to quickly skip forward generations, and lets the simulation
automatically run repeatedly without user input. "Count:" displays the number of creatures still alive, and "Energy:" shows the average energy of the surviving creatures.

Sprites used were originally created by Oryx (https://forums.tigsource.com/index.php?topic=8970.0) and Justin Cyr (https://twitter.com/justin_cyr/status/634546317713391616)
