colorNames = ["maroon", "rose", "coral", "indigo", "navy", "slate",
          "olive", "amber", "beige", "teal", "mint", "aqua",
          "verdant", "forest", "ginger", "tan"]

for color in colorNames:
    f = open('bunting_'+color+'.json', 'w')
    f.write('''{
    "conditions": [
        {
            "type": "supplementaries:flag",
            "flag": "bunting"
        }
    ],
    "group": "bunting",
    "type": "minecraft:crafting_shapeless",
    "ingredients": [
        {
            "item": "supplementaries:bunting"
        },
        {
            "item": "dye_depot:'''+color+'''_dye"
        }
    ],
    "result": {
        "item": "supplementaries:bunting",
        "count": 1,
        "nbt": {
            "Color": "'''+color+'''"
        }
    }
}''')
    f.close()
    f = open('bunting_'+color+'_2.json', 'w')
    f.write('''{
    "conditions": [
        {
            "type": "supplementaries:flag",
            "flag": "bunting"
        }
    ],
    "type": "minecraft:crafting_shaped",
    "pattern": [
        "111",
        "000",
        " 0 "
    ],
    "key": {
        "1": {
            "item": "minecraft:string"
        },
        "0": {
            "item": "dye_depot:'''+color+'''_wool"
        }
    },
    "result": {
        "item": "supplementaries:bunting",
        "nbt": {
            "Color": "'''+color+'''"
        },
        "count": 6
    }
}''')
    f.close()