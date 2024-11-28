package interface_adapter.profilesave;

import interface_adapter.profileview.ProfileViewModel;
import use_case.profilesave.ProfileSaveOutputBoundary;
import use_case.profilesave.ProfileSaveOutputData;

import javax.swing.*;

public class ProfileSavePresenter implements ProfileSaveOutputBoundary {
    private final ProfileViewModel viewModel;

    public ProfileSavePresenter(ProfileViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void presentProfile(ProfileSaveOutputData outputData) {
        viewModel.setYearOfStudy(outputData.getYearOfStudy());
        viewModel.setProgram(outputData.getProgram());
        viewModel.setBio(outputData.getBio());
        viewModel.setCollege(outputData.getCollege());
//        if (outputData.isSuccess()) {
//            // Optionally update the ViewModel if saving affects the displayed data
//            // For example, if saved fields need to be refreshed in the UI
//            viewModel.setYearOfStudy(outputData.getYearOfStudy());
//            viewModel.setProgram(outputData.getProgram());
//            viewModel.setBio(outputData.getBio());
//            viewModel.setCollege(outputData.getCollege());
//
//            // Show success message
//        } else {
//            JOptionPane.showMessageDialog(null, "Profile save was not successful", "Error", JOptionPane.ERROR_MESSAGE);
//        }
    }
}