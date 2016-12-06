//
//  SubmitQualityReportVC.swift
//  Clean Water Mapping
//
//  Created by Frances Tsenn on 12/5/16.
//  Copyright © 2016 Bits Please. All rights reserved.
//

import UIKit

class SubmitQualityReportVC: UIViewController, UIPickerViewDelegate, UIPickerViewDataSource, UITextFieldDelegate {
    
    let overallConditions = ["Safe", "Treatable", "Unsafe"]
    
    @IBOutlet weak var locationField : UITextView!
    @IBOutlet weak var conditionPicker : UIPickerView!
    @IBOutlet weak var virusPPMField:  UITextField!
    @IBOutlet weak var contaminantPPMField : UITextField!
    @IBOutlet weak var alertLabel : UILabel!
    
    var condition : String = ""
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.conditionPicker.delegate = self
        self.conditionPicker.dataSource = self
        
        condition = overallConditions[0]
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    @IBAction func submit(button: UIButton) {
        let location : String = locationField.text!
        guard let virusPPM : Float = Float.init(virusPPMField.text!), let contaminantPPM : Float = Float.init(contaminantPPMField.text!) else {
            alertLabel.text = "Please enter valid numbers for virus PPM and contaminant PPM."
            return
        }
        alertLabel.text = ""
        Data.sharedInstance.submitQualityReport(location: location, overallCondition: condition, virusPPM: virusPPM, contaminantPPM: contaminantPPM)
        
        //go back to main screen
        let storyboard = UIStoryboard.init(name: "Application", bundle: nil)
        //let navController = storyboard.instantiateViewController(withIdentifier: "WaterReportList")
        //self.present(navController, animated: true, completion: nil)
        switch Data.sharedInstance.currentUser.getType() {
        case .user:
            let navController = storyboard.instantiateViewController(withIdentifier: "UserNC")
            self.present(navController, animated: true, completion: nil)
        case .worker:
            let navController = storyboard.instantiateViewController(withIdentifier: "WorkerNC")
            self.present(navController, animated: true, completion: nil)
        case .manager:
            let navController = storyboard.instantiateViewController(withIdentifier: "ManagerNC")
            self.present(navController, animated: true, completion: nil)
        case .admin:
            let navController = storyboard.instantiateViewController(withIdentifier: "AdminNC")
            self.present(navController, animated: true, completion: nil)
        }
    }
    
    func numberOfComponents(in: UIPickerView) -> Int {
        return 1
    }
    
    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        return overallConditions.count
    }
    
    func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        return overallConditions[row]
    }
    
    func pickerView(_ pickerView: UIPickerView, attributedTitleForRow row: Int, forComponent component: Int) -> NSAttributedString? {
        let string = overallConditions[row]
        return NSAttributedString(string: string, attributes: [NSForegroundColorAttributeName:UIColor.white])
    }
    
    // Catpure the picker view selection
    func pickerView(_ pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int) {
        // This method is triggered whenever the user makes a change to the picker selection.
        // The parameter named row and component represents what was selected.
        condition = overallConditions[row]
    }
    
    func textField(_ textField: UITextField, shouldChangeCharactersIn range: NSRange, replacementString string: String) -> Bool {
        if (string.characters.count > 0) {
            let numbers = CharacterSet(charactersIn: "0123456789,.")
            let charsTextField = CharacterSet(charactersIn: string)
            
            return numbers.isSuperset(of: charsTextField)
        }
        return true
    }
}
