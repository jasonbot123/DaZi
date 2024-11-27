//package use_case.profilesave;
//
//import org.bson.Document;
//import use_case.profilesave.ProfileSaveDataAccessInterface;
//
//public class ProfileSaveInteractor {
//    private final ProfileSaveDataAccessInterface dataAccess;
//    private final ProfileSaveOutputBoundary presenter;
//
//    public ProfileSaveInteractor(ProfileSaveDataAccessInterface dataAccess, ProfileSaveOutputBoundary presenter) {
//        this.dataAccess = dataAccess;
//        this.presenter = presenter;
//    }
//
//    public void saveProfile(ProfileSaveInputData inputData) {
//        Document profileDoc = new Document("userId", inputData.getUsername())
//                .append("yearOfStudy", inputData.getYearOfStudy())
//                .append("program", inputData.getProgram())
//                .append("bio", inputData.getBio())
//                .append("college", inputData.getCollege());
//
//        try {
//            dataAccess.saveProfile(inputData.getUsername(), profileDoc);
//
//            ProfileSaveOutputData outputData = new ProfileSaveOutputData();
//            outputData.setSuccess(true);
//            outputData.setMessage("Profile saved successfully.");
//            presenter.presentSaveResult(outputData);
//        } catch (Exception e) {
//            ProfileSaveOutputData outputData = new ProfileSaveOutputData();
//            outputData.setSuccess(false);
//            outputData.setMessage("Failed to save profile: " + e.getMessage());
//            presenter.presentSaveResult(outputData);
//        }
//    }
//}