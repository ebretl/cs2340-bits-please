//
//  RegistrationVC.swift
//  Clean Water Mapping
//
//  Created by Frances Tsenn on 12/3/16.
//  Copyright © 2016 Bits Please. All rights reserved.
//

import UIKit

class RegistrationVC: UIViewController, UIPickerViewDataSource, UIPickerViewDelegate {
    
    @IBOutlet weak var fullNameField : UITextField!
    @IBOutlet weak var usernameField : UITextField!
    @IBOutlet weak var passwordField : UITextField!
    @IBOutlet weak var userTypePicker : UIPickerView!
    @IBOutlet weak var alertLabel : UILabel!
    
    var userTypes = ["User", "Manager", "Worker", "Admin"]
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.userTypePicker.delegate = self
        self.userTypePicker.dataSource = self
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    @IBAction func register(button: UIButton) {
        let fullName : String = fullNameField.text!
        let username : String = usernameField.text!
        let password : String = passwordField.text!
        if (fullName.isEmpty || username.isEmpty || password.isEmpty) {
            alertLabel.text = "You need your full name, username, and password to register."
            return
        } else {
            
        }
    }
    
    func numberOfComponents(in: UIPickerView) -> Int {
        return 1
    }
    
    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        return userTypes.count
    }
    
    func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        return userTypes[row]
    }
    
    func pickerView(_ pickerView: UIPickerView, attributedTitleForRow row: Int, forComponent component: Int) -> NSAttributedString? {
        
        let string = userTypes[row]
        return NSAttributedString(string: string, attributes: [NSForegroundColorAttributeName:UIColor.white])
    }
}
