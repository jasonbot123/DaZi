//package data_access;
//
//public class MongoUserProfileSaveDataAccessObject {
//
//
//
//
//    @Override
//    public void saveProfile(ProfileSaveInputData profile) {
//        // Convert ProfileInputData to Document
//        Document profileDoc = profileInputDataToDocument(profile);
//
//        // Update the profile in MongoDB or insert if not existing
//        profilesCollection.replaceOne(eq("username", profile.getUsername()), profileDoc, new com.mongodb.client.model.ReplaceOptions().upsert(true));
//    }
//}
