colorNames = ["maroon", "rose", "coral", "indigo", "navy", "slate",
          "olive", "amber", "beige", "teal", "mint", "aqua",
          "verdant", "forest", "ginger", "tan"]

for color in colorNames:
    f = open('sack_'+color+'.json', 'w')
    f.write('''{
  "type": "minecraft:block",
  "pools": [
    {
      "rolls": 1.0,
      "bonus_rolls": 0.0,
      "entries": [
        {
          "type": "minecraft:item",
          "functions": [
            {
              "function": "minecraft:copy_name",
              "source": "block_entity"
            },
            {
              "function": "minecraft:copy_nbt",
              "source": "block_entity",
              "ops": [
                {
                  "source": "Lock",
                  "target": "BlockEntityTag.Lock",
                  "op": "replace"
                },
                {
                  "source": "LootTable",
                  "target": "BlockEntityTag.LootTable",
                  "op": "replace"
                },
                {
                  "source": "LootTableSeed",
                  "target": "BlockEntityTag.LootTableSeed",
                  "op": "replace"
                }
              ]
            },
            {
              "function": "minecraft:set_contents",
              "type": "minecraft:shulker_box",
              "entries": [
                {
                  "type": "minecraft:dynamic",
                  "name": "minecraft:contents"
                }
              ]
            }
          ],
          "name": "suppsquared:sack_'''+color+'''"
        }
      ]
    }
  ]
}''')
    f.close()
    f = open('gold_candle_holder_'+color+'.json', 'w')
    f.write('''{
  "type": "minecraft:block",
  "pools": [
    {
      "rolls": 1.0,
      "bonus_rolls": 0.0,
      "entries": [
        {
          "type": "minecraft:item",
          "functions": [
            {
              "function": "minecraft:set_count",
              "conditions": [
                {
                  "condition": "minecraft:block_state_property",
                  "block": "suppsquared:gold_candle_holder_'''+color+'''",
                  "properties": {
                    "candles": "2"
                  }
                }
              ],
              "count": 2.0,
              "add": false
            },
            {
              "function": "minecraft:set_count",
              "conditions": [
                {
                  "condition": "minecraft:block_state_property",
                  "block": "suppsquared:gold_candle_holder_'''+color+'''",
                  "properties": {
                    "candles": "3"
                  }
                }
              ],
              "count": 3.0,
              "add": false
            },
            {
              "function": "minecraft:set_count",
              "conditions": [
                {
                  "condition": "minecraft:block_state_property",
                  "block": "suppsquared:gold_candle_holder_'''+color+'''",
                  "properties": {
                    "candles": "4"
                  }
                }
              ],
              "count": 4.0,
              "add": false
            },
            {
              "function": "minecraft:explosion_decay"
            }
          ],
          "name": "suppsquared:gold_candle_holder_'''+color+'''"
        }
      ]
    }
  ]
}''')
    f.close()