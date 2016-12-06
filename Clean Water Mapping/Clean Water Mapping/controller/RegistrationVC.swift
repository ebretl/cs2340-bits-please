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
    
    var userTypes = ["User", "Worker", "Manager", "Admin"]
    var type = UserTypeEnum.user
    
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
            let user = User(username: username, fullName: fullName, password: password, ban: false, type: type, emailAddr: "")
            if (Data.sharedInstance.registerUser(user: user)) {
                alertLabel.text = "You good."
                
                //go to main application screen
                let storyboard = UIStoryboard.init(name: "Application", bundle: nil)
                
                switch user.getType() {
                case .user:
                    let navController = storyboard.instantiateViewController(withIdentifier: "UserNC")
                    self.present(navController, animated: false, completion: nil)
                case .worker:
                    let navController = storyboard.instantiateViewController(withIdentifier: "WorkerNC")
                    self.present(navController, animated: false, completion: nil)
                case .manager:
                    let navController = storyboard.instantiateViewController(withIdentifier: "ManagerNC")
                    self.present(navController, animated: false, completion: nil)
                case .admin:
                    let navController = storyboard.instantiateViewController(withIdentifier: "AdminNC")
                    self.present(navController, animated: false, completion: nil)
                }

            } else {
                alertLabel.text = "This user is already in the system."
            }
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
    
    // Catpure the picker view selection
    func pickerView(_ pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int) {
        // This method is triggered whenever the user makes a change to the picker selection.
        // The parameter named row and component represents what was selected.
        switch row {
        case 1:
            type = UserTypeEnum.worker
        case 2:
            type = UserTypeEnum.manager
        case 3:
            type = UserTypeEnum.admin
        default:
            type = UserTypeEnum.user
        }
    }
}
