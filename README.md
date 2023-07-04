# Restrictedbackshulkers
> A CMI Extension by The456gamer

set per-shulkerbox overrides cmi backpacks  
prevent access to some shulkers as backpacks, make others cheaper / free / more expensive  
stored in nbt, and kept even after it is placed down / broken  

## requires:
- modern cmi (tested and compiled with CMI-9.6.0.9.jar, CMILib1.3.1.1.jar)
- minecraft 1.20.1
- latest paper (tested on Paper version git-Paper-63 (MC: 1.20.1) (Implementing API version 1.20.1-R0.1-SNAPSHOT) (Git: c0936a7))

## commands and permissions
`restrictedbackshulkers.command.execute` is needed for all commands,  
to "know its existance", otherwise it will say command not found and not tabcomplete  


### `/restrictedbackshulkers`
view current cost / opening  
requires `restrictedbackshulkers.command.execute` and `restrictedbackshulkers.command.view`  

### `/restrictedbackshulkers [cost]`
set new cost / opening of the item in your hand
requires `restrictedbackshulkers.command.execute` and `restrictedbackshulkers.command.set`  
if $cost is less than 0, opening will be fully prevented  
if $cost is exactly 0, opening will be free  
otherwise, $cost will be charged on opening the shulkerbox as a backpack  

### `/restrictedbackshulkers default`
reset to default (in cmi config) of the item in your hand  
requires `restrictedbackshulkers.command.execute` and `restrictedbackshulkers.command.set`  
it will remove any custom cost / restrictions  
