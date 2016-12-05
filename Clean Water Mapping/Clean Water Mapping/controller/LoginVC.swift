//
//  LoginVC.swift
//  Clean Water Mapping
//
//  Created by Frances Tsenn on 12/3/16.
//  Copyright © 2016 Bits Please. All rights reserved.
//

import UIKit

class LoginVC: UIViewController {
    
    @IBOutlet weak var usernameField: UITextField!
    @IBOutlet weak var passwordField: UITextField!
    @IBOutlet weak var alertLabel : UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    @IBAction func login(button: UIButton) {
        let username : String = usernameField.text!
        let password : String = passwordField.text!
        if (username.isEmpty || password.isEmpty) {
            alertLabel.text = "Please enter both your username and password."
            return
        } else {
            let user = Data.sharedInstance.login(username: username, password: password)
            if (user.checkUserBlocked()) {
                alertLabel.text = "You have incorrectly entered your username and password too many times. Your account is blocked."
            } else if (user.getUsername() == "") {
                alertLabel.text = "Your username or password is incorrect."
            } else {
                alertLabel.text = "You good."
                
                
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
                default: ()
                }
            

            }
        }
    }
}

