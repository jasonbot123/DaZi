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
    public void presentSaveResult(ProfileSaveOutputData outputData) {
        if (outputData.isSuccess()) {
            // Optionally update the ViewModel if saving affects the displayed data
            // For example, if saved fields need to be refreshed in the UI
            JOptionPane.showMessageDialog(null, outputData.getMessage(), "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, outputData.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}