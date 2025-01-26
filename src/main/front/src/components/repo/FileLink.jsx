import React, { useEffect, useState } from "react";
import MainDisplay from "../MainDisplay";
import { Box, Button, Card, CardContent, Typography } from "@mui/material";

import { ActionList, TreeView } from "@primer/react";
import {
  CheckboxIcon,
  DiffModifiedIcon,
  FileDirectoryIcon,
  FileIcon,
} from "@primer/octicons-react";
import { useFetchBe, useFetchGh } from "../../tools/api";
import { Link, useNavigate } from "react-router-dom";
import { useRecoilValue } from "recoil";
import { userDetailAtom } from "../../recoil/userAtom";
import FileLinkItem from "./FileLinkItem";
import { Octicon } from "@primer/react/deprecated";

function FileLink({ selectedFiles, setSelectedFiles, repo }) {
  const userDetail = useRecoilValue(userDetailAtom);
  const navigate = useNavigate();
  const fetchBe = useFetchBe();
  const fetchGh = useFetchGh();

  const [selectedFile, setSelectedFile] = useState(selectedFiles[0]);
  const [addedFiles, setAddedFiles] = useState([]);

  console.log(selectedFiles);
  return (
    <MainDisplay maxWidth={1200}>
      <Box sx={{ my: 2 }}>
        <Card>
          <CardContent>
            <Box
              display="flex"
              sx={{ my: 2 }}
              gap={2}
              justifyContent="space-between"
            >
              <Button component={Link} variant="contained" to="/">
                홈화면
              </Button>
              <Typography
                flexGrow={1}
                variant="h5"
                component="div"
                align="center"
              >
                파일 선택
              </Typography>
            </Box>

            <Box display="flex" gap={1}>
              <Card sx={{ width: 200, mt: 1 }}>
                <TreeView>
                  {selectedFiles.map((filename) => {
                    const added = addedFiles.find(
                      (item) => item.path === filename
                    );
                    return (
                      <TreeView.Item
                        key={filename}
                        id={filename}
                        current={filename === selectedFile}
                        onSelect={(e) => setSelectedFile(filename)}
                      >
                        <TreeView.LeadingVisual>
                          <FileIcon />
                        </TreeView.LeadingVisual>
                        {filename}
                        <TreeView.TrailingVisual
                          label={added ? "Added" : "Ready"}
                        >
                          {added ? (
                            <Octicon icon={CheckboxIcon} color="success.fg" />
                          ) : (
                            <Octicon
                              icon={DiffModifiedIcon}
                              color="attention.fg"
                            />
                          )}
                        </TreeView.TrailingVisual>
                      </TreeView.Item>
                    );
                  })}
                </TreeView>
              </Card>
              <Box flexGrow="1">
                <ActionList
                  role="menu"
                  selectionVariant="multiple"
                  aria-label="Project"
                >
                  {/* <ActionList.Heading as="h2" size="small">
              1단계. Repo 선택
              </ActionList.Heading> */}
                  <FileLinkItem
                    key={selectedFile}
                    filename={selectedFile}
                    ghUser={userDetail.gh}
                    repo={repo}
                    addedData={addedFiles.find(
                      (item) => item.path === selectedFile
                    )}
                    addItem={(data) =>
                      setAddedFiles((prev) => [
                        ...prev,
                        { ...data, path: selectedFile },
                      ])
                    }
                  />
                </ActionList>
              </Box>
              {/* <Button>다음</Button> */}
            </Box>
          </CardContent>
        </Card>
      </Box>
    </MainDisplay>
  );
}

export default FileLink;
