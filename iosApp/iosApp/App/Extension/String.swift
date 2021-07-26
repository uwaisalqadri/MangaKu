//
//  String.swift
//  iosApp
//
//  Created by Uwais Alqadri on 26/07/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation

extension String {

  func toDate(withFormat format: String = "yyyy-mm-ddThh:mm:ss.fffZ")-> Date? {
     let dateFormatter = DateFormatter()
     dateFormatter.calendar = Calendar(identifier: .gregorian)
     dateFormatter.dateFormat = format
     dateFormatter.locale = Locale.init(identifier: "id_ID")
     let date = dateFormatter.date(from: self)
     return date
   }
}
