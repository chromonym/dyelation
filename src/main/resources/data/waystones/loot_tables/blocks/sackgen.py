colorNames = ["maroon", "rose", "coral", "indigo", "navy", "slate",
          "olive", "amber", "beige", "teal", "mint", "aqua",
          "verdant", "forest", "ginger", "tan"]

for color in colorNames:
    f = open(color+'_sharestone.json', 'w')
    f.write('''{
  "type": "minecraft:block",
  "pools": [
    {
      "rolls": 1,
      "entries": [
        {
          "type": "minecraft:item",
          "name": "waystones:'''+color+'''_sharestone",
          "conditions": [
            {
              "condition": "minecraft:block_state_property",
              "block": "waystones:'''+color+'''_sharestone",
              "properties": {
                "half": "lower"
              }
            }
          ],
          "functions": [
            {
              "function": "minecraft:copy_nbt",
              "conditions": [
                {
                  "condition": "minecraft:match_tool",
                  "predicate": {
                    "enchantments": [
                      {
                        "enchantment": "minecraft:silk_touch",
                        "levels": {
                          "min": 1
                        }
                      }
                    ]
                  }
                }
              ],
              "source": "block_entity",
              "ops": [
                {
                  "source": "UUID",
                  "target": "UUID",
                  "op": "replace"
                }
              ]
            }
          ]
        }
      ],
      "conditions": [
        {
          "condition": "minecraft:survives_explosion"
        }
      ]
    }
  ]
}''')
    f.close()