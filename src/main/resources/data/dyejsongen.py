import json

ddNames = ["maroon", "rose", "coral", "indigo", "navy", "slate",
          "olive", "amber", "beige", "teal", "mint", "aqua",
          "verdant", "forest", "ginger", "tan"]

vNames = ["white", "orange", "magenta", "light_blue", "yellow", "lime",
          "pink", "gray", "light_gray", "cyan", "purple", "blue",
          "brown", "green", "red", "black"]

vanillaBlocks = {"bed": "dye_depot:C_bed",
                 "candle": "dye_depot:C_candle",
                 "candle_cake" : "dye_depot:C_candle_cake",
                 "glazed_terracotta" : "dye_depot:C_glazed_terracotta",
                 "shulker_box" : "dye_depot:C_shulker_box",
                 "stained_glass" : "dye_depot:C_stained_glass",
                 "stained_glass_pane" : "dye_depot:C_stained_glass_pane",
                 "terracotta" : "dye_depot:C_terracotta",
                 "wool" : "dye_depot:C_wool"}

vanillaItems = {"dyes": "dye_depot:C_dye",
                "pigments": "hexcasting:dye_colorizer_C"}

vJson = {}
blocks = {}
items = {}
for block in vanillaBlocks:
    inst = {}
    for color in ddNames:
        inst[vanillaBlocks[block].replace("C",color)] = color
    blocks[block] = inst
for item in vanillaItems:
    inst = {}
    for color in ddNames:
        inst[vanillaItems[item].replace("C",color)] = color
    items[item] = inst
vJson["blocks"] = blocks
vJson["items"] = items
#print(vJson)
vJ = json.dumps(vJson, indent=4)
with open("default.json", "w") as outfile:
    outfile.write(vJ)

newBlocks = {
    "another_furniture":{
        "stool": ["another_furniture:C_stool","dyelation:another_furniture/C_stool"],
        "curtain": ["another_furniture:C_curtain","dyelation:another_furniture/C_curtain"],
        "lamp": ["another_furniture:C_lamp","dyelation:another_furniture/C_lamp"],
        "lamp_connector": ["another_furniture:C_lamp_connector","dyelation:another_furniture/C_lamp_connector"],
        "sofa": ["another_furniture:C_sofa","dyelation:another_furniture/C_sofa"],
        "tall_stool": ["another_furniture:C_tall_stool","dyelation:another_furniture/C_tall_stool"]
    },
    "desire":{
        "concrete_bricks": ["desire:C_concrete_bricks", "dyelation:desire/C_concrete_bricks"],
        "concrete_brick_stairs": ["desire:C_concrete_brick_stairs", "dyelation:desire/C_concrete_brick_stairs"],
        "concrete_brick_slab": ["desire:C_concrete_brick_slab", "dyelation:desire/C_concrete_brick_slab"],
        "concrete_brick_wall": ["desire:C_concrete_brick_wall", "dyelation:desire/C_concrete_brick_wall"],
        "terracotta_mosaic": ["desire:C_terracotta_mosaic", "dyelation:desire/C_terracotta_mosaic"],
        "terracotta_mosaic_stairs": ["desire:C_terracotta_mosaic_stairs", "dyelation:desire/C_terracotta_mosaic_stairs"],
        "terracotta_mosaic_slab": ["desire:C_terracotta_mosaic_slab", "dyelation:desire/C_terracotta_mosaic_slab"],
        "terracotta_mosaic_wall": ["desire:C_terracotta_mosaic_wall", "dyelation:desire/C_terracotta_mosaic_wall"]
    },
    "farmersdelight":{
        "canvas_sign": ["farmersdelight:C_canvas_sign", "dyelation:farmersdelight/C_canvas_sign"],
        "canvas_wall_sign": ["farmersdelight:C_canvas_wall_sign", "dyelation:farmersdelight/C_canvas_wall_sign"],
        "hanging_canvas_sign": ["farmersdelight:C_hanging_canvas_sign", "dyelation:farmersdelight/C_hanging_canvas_sign"],
        "wall_hanging_canvas_sign": ["farmersdelight:C_wall_hanging_canvas_sign", "dyelation:farmersdelight/C_wall_hanging_canvas_sign"],
    },
    "spelunkery":{
        "glowstick": ["spelunkery:C_glowstick", "dyelation:spelunkery/C_glowstick"]
    },
    "verdant":{
        "primrose": ["verdant:C_primrose", "dyelation:verdant/C_primrose"]
    }
}
newItems = {
    "bundle-backportish":{
        "bundle": ["bundle-backportish:C_bundle","dyelation:bundle-backportish/C_bundle"]
    },
    "items_displayed":{
        "jewelry_pillow": ["items_displayed:C_jewelry_pillow","dyelation:items_displayed/C_jewelry_pillow"]
    }
}
oldBlocks = {
    "adorn":{
        "sofa": "adorn:C_sofa",
        "table_lamp": "adorn:C_table_lamp",
        "candlelit_lantern": "adorn:C_candlelit_lantern"
    },
    "supplementaries": {
        "awning": "supplementaries:awning_C",
        "present": "supplementaries:present_C",
        "trapped_present": "supplementaries:trapped_present_C",
        "candle_holder": "supplementaries:candle_holder_C",
    },
    "suppsquared": {
        "gold_candle_holder": "suppsquared:gold_candle_holder_C",
        "sack": "suppsquared:sack_C",
    },
}
oldItems = {
    "waystones": {
        "sharestone": "waystones:C_sharestone"
    }
}

# old stuff
for mod in newBlocks:
    modJson = {}
    blocks = {}
    for block in newBlocks[mod]:
        inst = {}
        for color in vNames:
            inst[newBlocks[mod][block][0].replace("C",color)] = color
        blocks[mod+":"+block] = inst
    modJson["blocks"] = blocks
    modJson["items"] = {}
    vJ = json.dumps(modJson, indent=4)
    with open(mod+"_default.json", "w") as outfile:
        outfile.write(vJ)
for mod in newItems:
    modJson = {}
    items = {}
    for item in newItems[mod]:
        inst = {}
        for color in vNames:
            inst[newItems[mod][item][0].replace("C",color)] = color
        items[mod+":"+item] = inst
    modJson["blocks"] = {}
    modJson["items"] = items
    vJ = json.dumps(modJson, indent=4)
    with open(mod+"_default.json", "w") as outfile:
        outfile.write(vJ)
for mod in oldBlocks:
    modJson = {}
    blocks = {}
    for block in oldBlocks[mod]:
        inst = {}
        for color in vNames:
            inst[oldBlocks[mod][block].replace("C",color)] = color
        blocks[mod+":"+block] = inst
    modJson["blocks"] = blocks
    modJson["items"] = {}
    vJ = json.dumps(modJson, indent=4)
    with open(mod+"_default.json", "w") as outfile:
        outfile.write(vJ)
for mod in oldItems:
    modJson = {}
    items = {}
    for item in oldItems[mod]:
        inst = {}
        for color in vNames:
            inst[oldItems[mod][item].replace("C",color)] = color
        items[mod+":"+item] = inst
    modJson["blocks"] = {}
    modJson["items"] = items
    vJ = json.dumps(modJson, indent=4)
    with open(mod+"_default.json", "w") as outfile:
        outfile.write(vJ)

# new stuff
dJson = {}
blocks = {}
items = {}

for mod in newBlocks:
    modJson = {}
    blocks = {}
    for block in newBlocks[mod]:
        inst = {}
        for color in ddNames:
            inst[newBlocks[mod][block][1].replace("C",color)] = color
        blocks[mod+":"+block] = inst
    modJson["blocks"] = blocks
    modJson["items"] = {}
    vJ = json.dumps(modJson, indent=4)
    with open(mod+".json", "w") as outfile:
        outfile.write(vJ)
for mod in newItems:
    modJson = {}
    items = {}
    for item in newItems[mod]:
        inst = {}
        for color in ddNames:
            inst[newItems[mod][item][1].replace("C",color)] = color
        items[mod+":"+item] = inst
    modJson["blocks"] = {}
    modJson["items"] = items
    vJ = json.dumps(modJson, indent=4)
    with open(mod+".json", "w") as outfile:
        outfile.write(vJ)
for mod in oldBlocks:
    modJson = {}
    blocks = {}
    for block in oldBlocks[mod]:
        inst = {}
        for color in ddNames:
            inst[oldBlocks[mod][block].replace("C",color)] = color
        blocks[mod+":"+block] = inst
    modJson["blocks"] = blocks
    modJson["items"] = {}
    vJ = json.dumps(modJson, indent=4)
    with open(mod+".json", "w") as outfile:
        outfile.write(vJ)
for mod in oldItems:
    modJson = {}
    items = {}
    for item in oldItems[mod]:
        inst = {}
        for color in ddNames:
            inst[oldItems[mod][item].replace("C",color)] = color
        items[mod+":"+item] = inst
    modJson["blocks"] = {}
    modJson["items"] = items
    vJ = json.dumps(modJson, indent=4)
    with open(mod+".json", "w") as outfile:
        outfile.write(vJ)
