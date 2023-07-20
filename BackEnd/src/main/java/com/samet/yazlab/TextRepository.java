package com.samet.yazlab;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.rmi.server.ObjID;

@Repository
public interface TextRepository extends MongoRepository<Text, ObjectId> {
}
