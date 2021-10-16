/*
 *  Created under not commercial project "Make it fine"
 *
 *  Copyright 2017-2021
 *  @author stingion
 */

db = db.getSiblingDB("introdb");
db.createCollection("info");
db.info.createIndex({"key": 1}, {"unique": true});
db.info.insert({"key": "author", "details": "koresmosto@gmail.com"});
db.info.insert({"key": "purpose", "details": "Social Network for workers"});
db.info.insert({"key": "stage", "details": "Development stage"});
