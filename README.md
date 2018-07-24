# Wager

Wager is a gambling plugin for Minecraft. It allows players to create Bartenders that sell a variety of drinks that they configure.
The inventory size scales depending on the amount of drinks you created in the config. All item metadata can be configured as well.

![Showcase](https://i.imgur.com/wRuufpR.gif)

## Dependencies
- Bukkit/Spigot
- Citizens
- Vault

## Example Config
```YAML bartender-name: '&dBartender'
bar-name: '&5The Hay Barrel'
price-lore: '&7Price: &e{price}'
reward-lore: '&7Reward: &a{reward}'
chance-lore: '&7Win Chance: &d{chance}'
you-won: '&a&lYEEHAW! &7You have won &a{reward} Bits&7!'
you-lost: '&c&lMush-Head! &7You lost &c{price} Bits&7. Better luck next time!'
cant-afford: '&cYou cannot afford this drink!'
error: '&cAn error has occured. Please contact an administrator.'
items:
  # Inventory slot for the item.
  0:
    # Display name
    name: '&aCactus Wine'
    # Cost to purchase
    price: 5000
    # Winning amount
    reward: 10000
    # Chance of winning
    chance: 60
    # Itemstack material
    material: POTION
  1:
    # Display name
    name: '&6Mule Skinner'
    # Cost to purchase
    price: 10000
    # Winning amount
    reward: 20000
    # Chance of winning
    chance: 50
    # Itemstack material
    material: POTION
  2:
    # Display name
    name: '&5Tequila'
    # Cost to purchase
    price: 25000
    # Winning amount
    reward: 50000
    # Chance of winning
    chance: 50
    # Itemstack material
    material: POTION
  3:
    # Display name
    name: '&cPeyote Tea'
    # Cost to purchase
    price: 50000
    # Winning amount
    reward: 100000
    # Chance of winning
    chance: 50
    # Itemstack material
    material: POTION
  4:
    # Display name
    name: '&2Blackberry Liquor'
    # Cost to purchase
    price: 100000
    # Winning amount
    reward: 200000
    # Chance of winning
    chance: 50
    # Itemstack material
    material: POTION
```
