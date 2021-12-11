//
//  View.swift
//  iosApp
//
//  Created by Uwais Alqadri on 17/09/21.
//  Copyright © 2021 Uwais Alqadri. All rights reserved.
//

import SwiftUI

extension View {
  func customDialog<DialogContent: View>(
    isShowing: Binding<Bool>,
    @ViewBuilder dialogContent: @escaping () -> DialogContent
  ) -> some View {
    self.modifier(CustomDialogView(isShowing: isShowing, dialogContent: dialogContent))
  }
}
