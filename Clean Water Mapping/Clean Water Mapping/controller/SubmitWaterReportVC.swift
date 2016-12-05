//
//  SubmitWaterReportVC.swift
//  Clean Water Mapping
//
//  Created by Frances Tsenn on 12/4/16.
//  Copyright © 2016 Bits Please. All rights reserved.
//

import UIKit

class SubmitWaterReportVC: UIViewController, UIPickerViewDelegate, UIPickerViewDataSource {
    
    let waterTypes = ["Bottled", "Well", "Stream", "Lake", "Spring", "Other"]
    let waterConditions = ["Waste", "Treatable (Clear)", "Treatable (Muddy)", "Potable"]
    
    @IBOutlet weak var locationField : UITextView!
    @IBOutlet weak var typePicker : UIPickerView!
    @IBOutlet weak var conditionPicker : UIPickerView!
    
    var type : String = ""
    var condition : String = ""
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.typePicker.delegate = self
        self.typePicker.dataSource = self
        self.conditionPicker.delegate = self
        self.typePicker.dataSource = self
        
        type = waterTypes[0]
        condition = waterConditions[0]
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    @IBAction func submit(button: UIButton) {
        let location : String = locationField.text!
        Data.sharedInstance.submitWaterReport(location: location, waterType: type, waterCondition: condition)
        
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
        default: ()
        }
    }
    
    func numberOfComponents(in: UIPickerView) -> Int {
        return 1
    }
    
    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        switch pickerView.tag {
        case 0:
            return waterTypes.count
        case 1:
            return waterConditions.count
        default:
            return 0
        }
    }
    
    func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        switch pickerView.tag {
        case 0:
            return waterTypes[row]
        case 1:
            return waterConditions[row]
        default:
            return nil
        }
    }
    
    func pickerView(_ pickerView: UIPickerView, attributedTitleForRow row: Int, forComponent component: Int) -> NSAttributedString? {
        switch pickerView.tag {
        case 0:
            let string = waterTypes[row]
            return NSAttributedString(string: string, attributes: [NSForegroundColorAttributeName:UIColor.white])
        case 1:
            let string = waterConditions[row]
            return NSAttributedString(string: string, attributes: [NSForegroundColorAttributeName:UIColor.white])
        default:
            return NSAttributedString()
        }
    }
    
    // Catpure the picker view selection
    func pickerView(_ pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int) {
        // This method is triggered whenever the user makes a change to the picker selection.
        // The parameter named row and component represents what was selected.
        switch pickerView.tag {
        case 0:
            type = waterTypes[row]
        case 1:
            condition = waterConditions[row]
        default:
            return
        }
    }

}
