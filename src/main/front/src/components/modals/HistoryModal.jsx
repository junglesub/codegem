import { useState, useEffect } from "react";
import {
  Dialog,
  DialogTitle,
  DialogContent,
  DialogActions,
  Button,
  List,
  ListItem,
  ListItemText,
  Typography,
  Collapse,
  Box,
} from "@mui/material";

import DiffViewer from "react-diff-viewer-continued";
import Grid from "@mui/material/Grid2";

import { useFetchBe } from "../../tools/api";
import { calculateDiffChange, formatTimestamp } from "../../tools/tools";

const getColor = (diffChange) => {
  return diffChange === "최초" || diffChange === "일치"
    ? "lightgray"
    : diffChange.startsWith("-")
    ? "red"
    : "green";
};

const HistoryModal = ({ openState, item }) => {
  const [open, setOpen] = openState;

  const [openRevisionId, setOpenRevisionId] = useState(null);
  const [historyData, setHistoryData] = useState(null);

  const handleToggle = (id) => {
    setOpenRevisionId(openRevisionId === id ? null : id);
  };

  const fetchBe = useFetchBe();

  useEffect(() => {
    if (!open || !item?.subjectId) return;
    fetchBe(`/subject/${item.subjectId}`).then((doc) => {
      setHistoryData(
        doc?.messageHistory
          ?.map((entry, idx, arr) => ({
            id: entry.id,
            time: formatTimestamp(entry.sentAt * 1000, true),
            diff: {
              oldValue: arr.length > idx + 1 ? arr[idx + 1].message : "",
              newValue: entry.message,
            },
            // diffChange: "+10",
          }))
          .map((item, idx, arr) => ({
            ...item,
            diffChange:
              idx === arr.length - 1
                ? "최초"
                : calculateDiffChange(item.diff.oldValue, item.diff.newValue),
          }))
      );
    });
    // fetchBe(`/kafeed/sharehash/${item.id}`).then((doc) => setShareHash(doc));
  }, [fetchBe, item?.subjectId, open]);

  const handleClose = () => {
    setOpen(false);
    setOpenRevisionId(null);
  };
  return (
    <>
      <Dialog fullWidth open={open} onClose={handleClose}>
        <DialogTitle>
          기록보기
          <Typography variant="body2" color="textSecondary">
            (11월 6일 이전에 작성된 동일 메세지는 별도로 표시되지 않습니다.)
          </Typography>
        </DialogTitle>
        <DialogContent>
          {historyData && (
            <List>
              {historyData.map((revision) => (
                <div key={revision.id}>
                  <ListItem
                    button="true"
                    onClick={() => handleToggle(revision.id)}
                    style={{ cursor: "pointer" }}
                  >
                    <Grid container alignItems="center">
                      <Grid xs={8}>
                        <ListItemText primary={revision.time} />
                      </Grid>
                      {revision.diffChange && (
                        <Grid xs={4}>
                          <Box
                            component="span"
                            sx={{
                              display: "inline-block",
                              backgroundColor: getColor(revision.diffChange),
                              borderRadius: "4px",
                              color: "white",
                              padding: "2px 8px",
                              marginLeft: "8px",
                            }}
                          >
                            {`${revision.diffChange}`}
                          </Box>
                        </Grid>
                      )}
                    </Grid>
                  </ListItem>
                  <Collapse
                    in={openRevisionId === revision.id}
                    timeout="auto"
                    unmountOnExit
                    sx={{
                      px: 2,
                    }}
                  >
                    <Typography variant="body2" color="textSecondary">
                      수정된 메세지:
                    </Typography>
                    <Box
                      sx={{
                        overflowX: "hidden", // Disable horizontal scroll
                        whiteSpace: "pre-wrap", // Preserve whitespace but allow wrapping
                        wordBreak: "break-word", // Break long words to fit
                      }}
                    >
                      <DiffViewer
                        oldValue={revision.diff.oldValue}
                        newValue={revision.diff.newValue}
                        splitView={false}
                        hideLineNumbers
                      />
                    </Box>
                  </Collapse>
                </div>
              ))}
            </List>
          )}
        </DialogContent>
        <DialogActions>
          <Box display="flex" flexDirection="column" gap={1} width="100%">
            <Button onClick={handleClose} color="secondary" fullWidth>
              확인
            </Button>
          </Box>
        </DialogActions>
      </Dialog>
    </>
  );
};

export default HistoryModal;
