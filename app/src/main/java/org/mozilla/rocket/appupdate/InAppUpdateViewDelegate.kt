/* -*- Mode: Java; c-basic-offset: 4; tab-width: 20; indent-tabs-mode: nil; -*-
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.rocket.appupdate

import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.Toast
import org.mozilla.focus.activity.MainActivity

class InAppUpdateViewDelegate(
    private val activity: MainActivity,
    private val snackBarAnchor: View
) : InAppUpdateManager.InAppUpdateUIDelegate {

    override fun showInAppUpdateIntro(
        callback: InAppUpdateManager.InAppUpdateIntroCallback,
        data: InAppUpdateIntro
    ): Boolean {
        // TODO: Implement intro dialog after PR#3709 is landed

        val dialog = AlertDialog.Builder(activity)
                .setTitle(data.title)
                .setMessage(data.description)
                .setPositiveButton(data.positiveText) { _, _ -> callback.onPositive() }
                .setNegativeButton(data.negativeText) { _, _ -> callback.onNegative() }
                .setCancelable(false)
                .create()
        dialog.setCanceledOnTouchOutside(false)
        dialog.show()
        return true
    }

    override fun showInAppUpdateInstallPrompt(action: () -> Unit) {
        // TODO: String resource needed
        Snackbar.make(snackBarAnchor, "Update downloaded", Snackbar.LENGTH_LONG)
                .setAction("Update") { action.invoke() }
                .show()
    }

    override fun showInAppUpdateDownloadStartHint() {
        // TODO: String resource needed
        Toast.makeText(activity, "Download start", Toast.LENGTH_SHORT).show()
    }

    override fun closeOnInAppUpdateDenied() {
        activity.finish()
    }
}
