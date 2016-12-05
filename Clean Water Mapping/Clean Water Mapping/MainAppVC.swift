//
//  MainAppVC.swift
//  Clean Water Mapping
//
//  Created by Frances Tsenn on 12/4/16.
//  Copyright © 2016 Bits Please. All rights reserved.
//

import UIKit

class MainAppVC: UIViewController {
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    @IBAction func logout(button: UIBarButtonItem) {
        Data.sharedInstance.currentUser = User()
        
        //go back to welcome screen
        let storyboard = UIStoryboard.init(name: "Main", bundle: nil)
        let viewController = storyboard.instantiateInitialViewController()
        self.present(viewController!, animated: true, completion: nil)
    }
}
