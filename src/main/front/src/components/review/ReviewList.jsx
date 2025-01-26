import { Card } from "@mui/material";
import {
  CheckboxIcon,
  DiffModifiedIcon,
  FileIcon,
} from "@primer/octicons-react";
import { TreeView } from "@primer/react";
import { Octicon } from "@primer/react/deprecated";
import React, { useEffect, useState } from "react";
import { useFetchGh } from "../../tools/api";

function ReviewList({
  addedFiles = [],
  selectedIssue,
  setSelectedIssue,
  userRepoName,
  allIssues,
  allCommentedIssue,
}) {
  const fetchGh = useFetchGh();

  return (
    <Card>
      <TreeView>
        {allIssues.map((issue) => {
          const added = allCommentedIssue.find(
            (item) => item.number === issue.number
          );
          return (
            <TreeView.Item
              key={issue?.number}
              id={issue?.number}
              current={issue?.number === selectedIssue?.number}
              onSelect={(e) => setSelectedIssue(issue)}
            >
              {/* <TreeView.LeadingVisual>
                <FileIcon />
              </TreeView.LeadingVisual> */}
              {issue.title}
              <TreeView.TrailingVisual label={added ? "Added" : "Ready"}>
                {added ? (
                  <Octicon icon={CheckboxIcon} color="success.fg" />
                ) : (
                  <Octicon icon={DiffModifiedIcon} color="attention.fg" />
                )}
              </TreeView.TrailingVisual>
            </TreeView.Item>
          );
        })}
      </TreeView>
    </Card>
  );
}

export default ReviewList;
